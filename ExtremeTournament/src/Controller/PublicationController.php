<?php

namespace App\Controller;

use App\Entity\Commentaire;
use App\Entity\Publication;
use App\Form\CommentaireType;
use App\Form\PublicationType;
use App\Repository\UserRepository;
use Doctrine\DBAL\Types\TextType;
use Knp\Component\Pager\PaginatorInterface;
use phpDocumentor\Reflection\Types\Resource_;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\Form\Extension\Core\Type\SearchType;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Mailer\MailerInterface;
use Symfony\Component\Routing\Annotation\Route;
use MercurySeries\FlashyBundle\FlashyNotifier;
use Symfony\Component\Mime\Email;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Serializer\Annotation\Groups;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;


class PublicationController extends AbstractController
{
    /**
     * @Route("/publication", name="app_pubication")
     */
    public function index(): Response
    {
        return $this->render('publication/index.html.twig', [
            'controller_name' => 'PublicationController',
        ]);
    }
    /**
     * @route("/listepub",name="listepub")
     */
    public function listepub()
    {
        $commentaires = $this->getDoctrine()->getRepository(Commentaire::class)->findAll();

        $publications = $this->getDoctrine()->getRepository(Publication::class)->findAll();
        return $this->render('publication/afficheforum.html.twig', array('publications' => $publications,
            'commentaires'=>$commentaires));
    }

    /**
     * @route("/listepubF",name="listepubF")
     */
    public function listepubF(Request $request, PaginatorInterface $paginator)
    {

        $publications = $this->getDoctrine()->getRepository(Publication::class)->findAll();
        $commentaires = $this->getDoctrine()->getRepository(Commentaire::class)->findAll();
        $pagination = $paginator->paginate(
            $publications, // Requête contenant les données à paginer (ici nos articles)
            $request->query->getInt('page', 1), // Numéro de la page en cours, passé dans l'URL, 1 si aucune page
            6 // Nombre de résultats par page
        );



        return $this->render('blog/index.html.twig', array('publications' => $pagination,
            'commentaires'=>$commentaires));
    }


    /**
     *
     * @route("/listecomF{id}",name="listecomF")
     */
    public function listecommentsF2($id,FlashyNotifier $flashy)
    {


        $commentaires = $this->getDoctrine()->getRepository(Commentaire::class)->findByid_publication($id);

        $flashy->primary('those are the comments!', 'http://your-awesome-link.com');

        return $this->render('blog/comments.html.twig', array(
            'commentaires'=>$commentaires,'id'=>$id));

    }

    /**
     *
     * @route("/reportcomF{id}",name="reportecomF")
     */
    public function reportcomment($id,\Swift_Mailer $mailer)
    {


        $commentaires = $this->getDoctrine()->getRepository(Commentaire::class)->findbyid_comment($id)[0]
;

        $commentaires->setNbrReports($commentaires->getNbrReports()+1);
        $em = $this->getDoctrine()->getManager();
        $em->flush();
       if( $commentaires->getNbrReports()>3){

           $em = $this->getDoctrine()->getManager();
           $em->remove($commentaires);
           $em->flush();
       }

        $message = (new \Swift_Message('the comment with this id is reported'))
            ->setFrom('brahimfrigui7@gmail.com')
            ->setTo('my2ndfantay@gmail.com')
            ->setBody($id);
        $mailer->send($message);


        return $this->redirectToRoute("listepubF");


    }


    /**
     * @Route ("/listepubtri",name="pubtri")
     */

    public function tristparcommennts()
    {
        $publication = $this->getDoctrine()->getRepository(Publication::class)->triparcomment();
        $publications = $this->getDoctrine()->getRepository(Publication::class)-> nbrparcomment();

        return $this->render('home\index.html.twig', array(
            'publication'=>$publication,'number'=>$publications));

    }
    /**
     *  @route("/deletepub/{id}", name="d")
     */
    public function deletepub($id)
    {
        $publication = $this->getDoctrine()->getRepository(Publication::class)->find($id);
        $em = $this->getDoctrine()->getManager();
        $em->remove($publication);
        $em->flush();
        return $this->redirectToRoute("listepub");
    }

    /**
     *  @route("/deletecomment/{id}", name="dc")
     */
    public function deletecomment($id)
    {
        $commentaires = $this->getDoctrine()->getRepository(Commentaire::class)->find($id);
        $em = $this->getDoctrine()->getManager();
        $em->remove($commentaires);
        $em->flush();
        return $this->redirectToRoute("listepub");
    }
    /**
     *  @route("/deletecommentF/{id}", name="dcF")
     */
    public function deletecommentF($id)
    {
        $commentaires = $this->getDoctrine()->getRepository(Commentaire::class)->find($id);
        $em = $this->getDoctrine()->getManager();
        $em->remove($commentaires);
        $em->flush();
        return $this->redirectToRoute("listepubF");
    }

    /**
 * @route ("/updatepub/{id}" , name="updatep")
 */

    public function updatepub(Request $request,$id)
    {
        $publication = $this->getDoctrine()->getRepository(Publication::class)->find($id);
        $form = $this->createForm(PublicationType::class, $publication);
        $form->add('modifier',SubmitType::class);
        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid()  ) {
            $em = $this->getDoctrine()->getManager();
            $em->flush();
            return $this->redirectToRoute('listepub');
        }
        return $this->render("publication/updateforum.html.twig",array('f'=>$form->createView()));
    }

    /**
     * @route ("/updatecomment/{id}" , name="updatecomment")
     */

    public function updatecomment(Request $request,$id)
    {
        $commentaires = $this->getDoctrine()->getRepository(Commentaire::class)->find($id);
        $form = $this->createForm(CommentaireType::class, $commentaires);
        $form->add('modifier',SubmitType::class);
        $form->handleRequest($request);
        if ($form->isSubmitted()) {
            $em = $this->getDoctrine()->getManager();
            $em->flush();
            return $this->redirectToRoute('listepub');
        }
        return $this->render("publication/updateforum.html.twig",array('form'=>$form->createView()));
    }


    /**
     * @Route("/addpub", name="addpub")
     */
    public function addpub(Request $request)
    {
        $publication = new Publication();
        $form = $this->createForm(PublicationType::class, $publication);
        $form->add('Ajouter',SubmitType::class);
        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid() ) {
            $em = $this->getDoctrine()->getManager();
            $em->persist($publication);
            $em->flush();




            return $this->redirectToRoute('listepub');
        }
        return $this->render("publication/addpub.html.twig",array('f'=>$form->createView()));
    }




    /**
     * @Route("/addcomment", name="addcomment")
     */
    public function addComment(Request $request)
    {

        $commentaires = new Commentaire();
        $form = $this->createForm(CommentaireType::class, $commentaires);
        $form->add('Ajouter',SubmitType::class);
        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->persist($commentaires);
            $em->flush();
            return $this->redirectToRoute('listepubF');
        }
        return $this->render("publication/addcomment.html.twig",array('f'=>$form->createView()));
    }

    /**
     *
     * @Route("/addcommentF/{id}", name="addcommentF")
     */
    public function addCommentF(Request $request,$id)
    {


        $publications =$this->getDoctrine()->getRepository(Publication::class)->find($id);

        $commentaires = new Commentaire();
        $form = $this->createForm(CommentaireType::class, $commentaires);
        $form->add('Ajouter',SubmitType::class);
        /*$form->get('id_publication')->setData($id);*/
        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $commentaires->setDateComment(new \DateTime());
            $commentaires->setidpublication($publications);
            $em->persist($commentaires);
            $em->flush();
            return $this->redirectToRoute('listepubF');
        }
        return $this->render("publication/addcommentF.html.twig",array('f'=>$form->createView()));
    }

    /**
     * @Route("/addpubF/", name="addpubF")
     */
    public function addpubF(Request $request,\Swift_Mailer $mailer)
    {
        $publication = new Publication();
        $form = $this->createForm(PublicationType::class, $publication);
        $form->add('Ajouter',SubmitType::class);
        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid() ) {
            $contact=$form->getData();
            $em = $this->getDoctrine()->getManager();
            $publication->setDateCreation(new \DateTime());
            $em->persist($publication);
            $em->flush();

            $message = (new \Swift_Message('A new post is added'))
                ->setFrom('brahimfrigui7@gmail.com')
                ->setTo('my2ndfantay@gmail.com')
                ->setBody($this->renderView('blog/Pubinfo.html.twig',compact('contact'),
                'text/html'));




            $mailer->send($message);

            return $this->redirectToRoute('listepubF');
        }
        return $this->render("publication/addpubF.html.twig",array('f'=>$form->createView()));
    }

    /**
     *  @route("/deletepubF/{id}", name="df")
     */
    public function deletepubF($id)
    {
        $publication = $this->getDoctrine()->getRepository(Publication::class)->find($id);
        $em = $this->getDoctrine()->getManager();
        $em->remove($publication);
        $em->flush();
        return $this->redirectToRoute("listepubF");
    }

    /**
     * @route ("/updatepubF/{id}" , name="updatepF")
     */

    public function updatepubF(Request $request,$id)
    {
        $publication = $this->getDoctrine()->getRepository(Publication::class)->find($id);
        $form = $this->createForm(PublicationType::class, $publication);
        $form->add('modifier',SubmitType::class);
        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid() ) {
            $em = $this->getDoctrine()->getManager();
            $em->flush();
            return $this->redirectToRoute('listepubF');
        }
        return $this->render("publication/updatepubF.html.twig",array('f'=>$form->createView()));
    }


    /**
     * @Route("/listpubMob", name="pub")
     */
    public function listepubMob(NormalizerInterface $Normalizer)
    {
        $equipe = $this->getDoctrine()->getRepository(Publication::class)->findAll();

        $formatted = $Normalizer->normalize($equipe,'json',['groups'=>'post:read']);
        return new Response(json_encode($formatted));

    }

    /**
     * @Route("/addpubmob", name="addpubmob")
     */
    public function addpubmob(Request $request,UserRepository $userRepository)
    {
        $equipe = new Publication();
        $user=$userRepository->find($request->get('id_user'));
        $status = $request->query->get("status");
        $titre = $request->query->get("titre");
        $id=$request->query->get("id_user");



        $request->setMethod('put');
        $equipe->setTitre($titre);
        $equipe->setDateCreation(new \DateTime());
        $equipe->setStatus($status);
        $equipe->setIdUser($user);

        $em = $this->getDoctrine()->getManager();
        $em->persist($equipe);
        $em->flush();
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($equipe);

        return new JsonResponse($formatted);
    }

    /**
     * @Route("/deletepubmob/{id}", name="deletepubmob")
     */
    public function deleteTeam(Request $request,$id)
    {
        $equipe = $this->getDoctrine()->getRepository(Publication::class)->find($id);
        $em = $this->getDoctrine()->getManager();
        $em->remove($equipe);
        $em->flush();
        $serialize = new Serializer([new ObjectNormalizer()]);
        $formatted = $serialize->normalize("Publication a ete supprimee avec success.");
        return new JsonResponse($formatted);



}

    /**
     * update to back
     * @Route("/Updatepubmob", name="Updatepubmob")
     *

     */
    public function updatepubmob(Request $request,$id)
    {
        $equipe = $this->getDoctrine()->getRepository(Publication::class)->find($id);

        $request->setMethod('put');
        $equipe->setTitre($request->get("titre"));
        $equipe->setDateCreation(new \DateTime());
        $equipe->setStatus($request->get("status"));


        $em = $this->getDoctrine()->getManager();
        $em->flush();
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($equipe);
        return new JsonResponse("Publication a ete modifiee avec success.");

    }
}



