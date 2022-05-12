<?php

namespace App\Entity;

use App\Repository\ProduitRepository;
use Doctrine\ORM\Mapping as ORM;

/**
 * @ORM\Entity(repositoryClass=ProduitRepository::class)
 */
class Produit
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private $refProd;

    /**
     * @ORM\Column(type="string", length=50)
     */
    private $nomProd;

    /**
     * @ORM\Column(type="float")
     */
    private $prix;

    /**
     * @ORM\Column(type="integer")
     */
    private $TotalEnStock;

    /**
     * @ORM\Column(type="string", length=50)
     */
    private $Descriptif;

    /**
     * @ORM\Column(type="string", length=50)
     */
    private $CategorieProd;

    /**
     * @ORM\Column(type="string", length=50)
     */
    private $disponibilite;

    /**
     * @ORM\ManyToOne(targetEntity=Panier::class, inversedBy="refProd")
     * @ORM\JoinColumn(name="id_panier",referencedColumnName="id_panier",nullable=false)
     */
    private $panier;


    public function getRefProd(): ?int
    {
        return $this->refProd;
    }

    public function setRefProd(int $refProd): self
    {
        $this->refProd = $refProd;

        return $this;
    }

    public function getNomProd(): ?string
    {
        return $this->nomProd;
    }

    public function setNomProd(string $nomProd): self
    {
        $this->nomProd = $nomProd;

        return $this;
    }

    public function getPrix(): ?float
    {
        return $this->prix;
    }

    public function setPrix(float $prix): self
    {
        $this->prix = $prix;

        return $this;
    }

    public function getTotalEnStock(): ?int
    {
        return $this->TotalEnStock;
    }

    public function setTotalEnStock(int $TotalEnStock): self
    {
        $this->TotalEnStock = $TotalEnStock;

        return $this;
    }

    public function getDescriptif(): ?string
    {
        return $this->Descriptif;
    }

    public function setDescriptif(string $Descriptif): self
    {
        $this->Descriptif = $Descriptif;

        return $this;
    }

    public function getCategorieProd(): ?string
    {
        return $this->CategorieProd;
    }

    public function setCategorieProd(string $CategorieProd): self
    {
        $this->CategorieProd = $CategorieProd;

        return $this;
    }

    public function getDisponibilite(): ?string
    {
        return $this->disponibilite;
    }

    public function setDisponibilite(string $disponibilite): self
    {
        $this->disponibilite = $disponibilite;

        return $this;
    }

    public function getPanier(): ?Panier
    {
        return $this->panier;
    }

    public function setPanier(?Panier $panier): self
    {
        $this->panier = $panier;

        return $this;
    }
}
